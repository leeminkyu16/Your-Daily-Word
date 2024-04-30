package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public class YdwGridPane extends GridPane implements YdwView {
	public YdwGridPane() {
		super();
	}

	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public void setColumnConstraints(
		@NotNull YdwObservable<double[]> widthWeights,
		HPos hAlignment
	) {
		this.setColumnConstraints(
			widthWeights.get(),
			hAlignment
		);

		WeakReference<YdwGridPane> weakReference = new WeakReference<>(this);
		this.addBeforeDestroyRunnable(
			widthWeights.addAfterChange(
				(oldValue, newValue) -> {
					YdwGridPane weakThis = weakReference.get();
					if (weakThis != null) {
						weakThis.setColumnConstraints(
							newValue,
							hAlignment
						);
					}
				}
			)
		);
	}

	public void setColumnConstraints(
		double[] widthWeights,
		HPos hAlignment
	) {
		this.setColumnConstraints(
			widthWeights,
			hAlignment,
			Priority.ALWAYS,
			true
		);
	}

	public void setColumnConstraints(
		YdwObservable<double[]> widthWeights,
		HPos hAlignment,
		Priority hGrow,
		boolean fillWidth
	) {
		this.setColumnConstraints(
			widthWeights.get(),
			hAlignment,
			hGrow,
			fillWidth
		);

		WeakReference<YdwGridPane> weakReference = new WeakReference<>(this);
		this.addBeforeDestroyRunnable(
			widthWeights.addAfterChange(
				(oldValue, newValue) -> {
					YdwGridPane weakThis = weakReference.get();
					if (weakThis != null) {
						weakThis.setColumnConstraints(
							newValue,
							hAlignment,
							hGrow,
							fillWidth
						);
					}
				}
			)
		);
	}

	public void setColumnConstraints(
		double[] widthWeights,
		HPos hAlignment,
		Priority hGrow,
		boolean fillWidth
	) {
		this.getColumnConstraints().clear();

		double totalWeight = Arrays.stream(widthWeights).sum();

		for (double widthWeight : widthWeights) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(widthWeight / totalWeight * 100);
			columnConstraints.setHalignment(hAlignment);
			columnConstraints.setHgrow(hGrow);
			columnConstraints.setFillWidth(fillWidth);

			this.getColumnConstraints().add(columnConstraints);
		}
	}

	public void setRowConstraints(
		YdwObservable<double[]> heightWeights,
		VPos vAlignment,
		Priority vGrow,
		boolean fillHeight
	) {
		this.setRowConstraints(
			heightWeights.get(),
			vAlignment,
			vGrow,
			fillHeight
		);

		WeakReference<YdwGridPane> weakReference = new WeakReference<>(this);
		this.addBeforeDestroyRunnable(
			heightWeights.addAfterChange(
				(oldValue, newValue) -> {
					YdwGridPane weakThis = weakReference.get();
					if (weakThis != null) {
						weakThis.setRowConstraints(
							newValue,
							vAlignment,
							vGrow,
							fillHeight
						);
					}
				}
			)
		);
	}

	public void setRowConstraints(
		double[] heightWeights,
		VPos vAlignment,
		Priority vGrow,
		boolean fillHeight
	) {
		this.getRowConstraints().clear();

		double totalWeight = Arrays.stream(heightWeights).sum();

		for (double heightWeight : heightWeights) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(heightWeight / totalWeight * 100);
			rowConstraints.setValignment(vAlignment);
			rowConstraints.setVgrow(vGrow);
			rowConstraints.setFillHeight(fillHeight);

			this.getRowConstraints().add(rowConstraints);
		}
	}

	public void safeAdd(Node child, int columnIndex, int rowIndex, int colspan, int rowspan) {
		if (child instanceof YdwView) {
			YdwWeakReference<YdwView> weakReference = new YdwWeakReference<>((YdwView) child);

			addBeforeDestroyRunnable(() -> weakReference.doIfNotNull(YdwView::beforeDestroy));
		}

		this.add(
			child,
			columnIndex,
			rowIndex,
			colspan,
			rowspan
		);
	}

	@Override
	public void connectViewModel(YdwViewModel viewModel) {
		this.connectViewModel(viewModel, beforeDestroyRunnables);
	}

	@Override
	public void setInternalViewModelReference(YdwViewModel viewModel) {
		this.viewModel.set(viewModel);
	}

	@Override
	public void addBeforeDestroyRunnable(Runnable... runnable) {
		this.addBeforeDestroyRunnable(beforeDestroyRunnables, runnable);
	}

	protected void removeBeforeDestroyRunnable(Collection<Runnable> collection) {
		this.beforeDestroyRunnables.removeAll(collection);
	}

	@Override
	public void beforeDestroy() {
		this.beforeDestroy(beforeDestroyRunnables);
	}
}
