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

public abstract class YdwDynamicRow extends YdwGridPane {
	public YdwDynamicRow() {
		this.setColumnConstraints(this.dynamicColumnWeights, HPos.CENTER, Priority.ALWAYS, true);
		this.setRowConstraints(this.dynamicRowWeights, VPos.CENTER, Priority.ALWAYS, true);
	}

	private final ArrayList<YdwDynamicColumnChildInformation> dynamicChildrenInfo = new ArrayList<>();

	public final YdwObservable<double[]> dynamicColumnWeights = new YdwObservable<>(new double[]{});
	private final YdwObservable<double[]> dynamicRowWeights = new YdwObservable<>(new double[]{1});
	private final ArrayList<Runnable> dynamicBeforeDestroyRunnables = new ArrayList<>();

	public void render() {
		beforeRender();
		renderBody();
		afterRender();
	}

	public YdwDynamicRow renderAndReturnThis() {
		this.render();
		return this;
	}

	private void beforeRender() {
		this.getChildren().removeAll(
			this.dynamicChildrenInfo.stream().flatMap(
				x -> x.columnChildren.stream()
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
		double[] columnWeights = this.dynamicChildrenInfo
			.stream()
			.filter(Objects::nonNull)
			.mapToDouble(YdwDynamicColumnChildInformation::columnWeight)
			.toArray();
		this.dynamicColumnWeights.set(columnWeights);

		for (int i = 0; i < this.dynamicChildrenInfo.size(); i++) {
			List<Node> columnChildren = this.dynamicChildrenInfo.get(i).columnChildren();

			for (int j = 0; j < columnChildren.size(); j++) {
				Node child = columnChildren.get(j);

				if (child instanceof YdwView) {
					YdwWeakReference<YdwView> weakReference = new YdwWeakReference<>((YdwView) child);

					Runnable beforeDestroy = () -> weakReference.doIfNotNull(YdwView::beforeDestroy);
					addBeforeDestroyRunnable(beforeDestroy);
					this.dynamicBeforeDestroyRunnables.add(beforeDestroy);
				}

				int rowSpan = Math.max(
					Math.floorDiv(
						this.dynamicRowWeights.get().length,
						columnChildren.size()
					) + (
						(this.dynamicRowWeights.get().length % columnChildren.size()) > j ? 1 : 0
					),
				1);

				this.add(child, i, j, 1, rowSpan);
			}

		}
	}

	public void renderAddColumn(Node child, double columnWeight) {
		this.renderAddColumn(
			List.of(child),
			columnWeight
		);
	}

	public void renderAddColumn(Node child1, Node child2, double columnWeight) {
		this.renderAddColumn(
			List.of(child1, child2),
			columnWeight
		);
	}

	public void renderAddColumn(List<Node> columnChildren, double columnWeight) {
		this.dynamicChildrenInfo.add(
			new YdwDynamicColumnChildInformation(columnChildren, columnWeight)
		);
	}

	private record YdwDynamicColumnChildInformation(
		List<Node> columnChildren,
		double columnWeight
	){}
}
