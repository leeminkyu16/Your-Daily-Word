package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.scene.control.TextFormatter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class YdwNumberTextField extends YdwTextField {
	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public YdwNumberTextField(
		YdwObservable<Integer> integerYdwObservable
	) {
		super();
		this.setTextFormatter(
			new TextFormatter<>(change -> {
				String text = change.getText();

				if (text.matches("[0-9,. ]*")) {
					return change;
				}

				return null;
			})
		);

		this.setText(integerYdwObservable.get().toString());
		this.addBeforeDestroyRunnable(
			integerYdwObservable.bind(
				this.textProperty(),
				Object::toString,
				(x) -> {
					String newString = x.replaceAll("[^0-9,.]", "");
					return Integer.parseInt(newString);
				}
			)
		);
	}

	public YdwNumberTextField applyStyleBundleAndReturnThis(YdwStyleBundle styleBundle) {
		styleBundle.apply(this);
		return this;
	}
}
