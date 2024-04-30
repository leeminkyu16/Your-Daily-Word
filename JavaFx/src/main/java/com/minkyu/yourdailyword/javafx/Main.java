package com.minkyu.yourdailyword.javafx;

import com.google.inject.Injector;
import com.minkyu.yourdailyword.javafx.components.main.MainScene;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.di.InjectorSingleton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		Injector injector = InjectorSingleton.getInjector();

		Scene scene = injector.getInstance(MainScene.class);

		IInternationalizationModel internationalizationModel = injector.getInstance(IInternationalizationModel.class);

		try (InputStream iconInputStream = Main.class.getResourceAsStream("Your-Daily-Word-Icon-Circular.png")) {
			if (iconInputStream != null) {
				stage.getIcons().add(new Image(iconInputStream));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		stage.setTitle(internationalizationModel.getString("your_daily_word"));
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}

	public static void main(String[] args) {
		launch();
	}
}
