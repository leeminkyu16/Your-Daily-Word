package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class YdwDynamicColumn extends YdwGridPane {
	public YdwDynamicColumn() {
		this.setColumnConstraints(this.dynamicColumnWeights, HPos.CENTER);
		this.setRowConstraints(this.dynamicRowWeights, VPos.CENTER, Priority.ALWAYS, true);
	}

	private final ArrayList<YdwDynamicColumnChildInformation> dynamicChildrenInfo = new ArrayList<>();

	public final YdwObservable<double[]> dynamicColumnWeights = new YdwObservable<>(new double[]{1});
	private final YdwObservable<double[]> dynamicRowWeights = new YdwObservable<>(new double[]{});
	private final ArrayList<Runnable> dynamicBeforeDestroyRunnables = new ArrayList<>();

	public void render() {
		beforeRender();
		renderBody();
		afterRender();
	}

	private void beforeRender() {
		this.getChildren().removeAll(
			this.dynamicChildrenInfo.stream().flatMap(
				x -> x.rowChildren.stream()
			).toList()
		);
		this.dynamicRowWeights.set(new double[]{});
		this.dynamicBeforeDestroyRunnables.forEach(Runnable::run);
		this.removeBeforeDestroyRunnable(dynamicBeforeDestroyRunnables);

		this.dynamicChildrenInfo.clear();
		this.dynamicBeforeDestroyRunnables.clear();
	}

	public abstract void renderBody();

	private void afterRender() {
		double[] rowWeights = this.dynamicChildrenInfo
			.stream()
			.filter(Objects::nonNull)
			.mapToDouble(YdwDynamicColumnChildInformation::rowWeight)
			.toArray();
		this.dynamicRowWeights.set(rowWeights);

		for (int i = 0; i < this.dynamicChildrenInfo.size(); i++) {
			List<Node> rowChildren = this.dynamicChildrenInfo.get(i).rowChildren();

			for (int j = 0; j < rowChildren.size(); j++) {
				Node child = rowChildren.get(j);

				if (child instanceof YdwView) {
					YdwWeakReference<YdwView> weakReference = new YdwWeakReference<>((YdwView) child);

					Runnable beforeDestroy = () -> weakReference.doIfNotNull(YdwView::beforeDestroy);
					addBeforeDestroyRunnable(beforeDestroy);
					this.dynamicBeforeDestroyRunnables.add(beforeDestroy);
				}

				int columnSpan = Math.max(
					Math.floorDiv(
						this.dynamicRowWeights.get().length,
						rowChildren.size()
					) + (
						(this.dynamicRowWeights.get().length % rowChildren.size()) > j ? 1 : 0
					),
				1);

				this.add(child, j, i, columnSpan, 1);
			}

		}
	}

	public void renderAddRow(Node child, double rowWeight) {
		this.renderAddRow(
			List.of(child),
			rowWeight
		);
	}

	public void renderAddRow(Node child1, Node child2, double rowWeight) {
		this.renderAddRow(
			List.of(child1, child2),
			rowWeight
		);
	}

	public void renderAddRow(List<Node> rowChildren, double rowWeight) {
		this.dynamicChildrenInfo.add(
			new YdwDynamicColumnChildInformation(rowChildren, rowWeight)
		);
	}

	private record YdwDynamicColumnChildInformation(
		List<Node> rowChildren,
		double rowWeight
	){}
}
