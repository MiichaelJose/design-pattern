package observer;

import java.util.ArrayList;
import java.util.List;

//Subject responsavel por definir a operação no registro
//registra quem recebera a notificação
//remove o responsavel
//forma como a notificação é transmitida
interface Subject {
	void registrarObserver(Observer o);
	void removerObserver(Observer o);
	void notificarObservers();
}

//Concrete Subject é o Observador de todos os objetos "observer" e armazenando, classe principal.
class SensorTemperatura implements Subject {
	private List<Observer> observers = new ArrayList<>();
	private double temperature;

	// quando a temperatura muda ele é chamado
	public void definirTemperatura(double temperature) {
		this.temperature = temperature;
		//todos os observadores registrados são notificados
		notificarObservers();
	}

	@Override
	public void registrarObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removerObserver(Observer o) {
		observers.remove(o);
	}

	// todos os observadores terao o mesmo valor atualizado
	@Override
	public void notificarObservers() {
		for (Observer observer : observers) {
			observer.atualizarObserver(temperature);
		}
	}
}

//Observer
interface Observer {
	void atualizarObserver(double temperature);
}

//Concrete Observer é um observador do subject de forma ser notificado quando o Subject tiver um mudança
class StatusTemperatura implements Observer {
	private double temperatureThreshold = 30;

	@Override
	public void atualizarObserver(double temperature) {
		if (temperature > temperatureThreshold) {
			System.out.println("A temperatura atingiu o limite. Ligando o Ar condicionado...");
		}
	}
}

public class Example {
	public static void main(String[] args) {
		SensorTemperatura temperatureSensor = new SensorTemperatura();
		StatusTemperatura thermostat = new StatusTemperatura();
		temperatureSensor.registrarObserver(thermostat);
		temperatureSensor.definirTemperatura(35);
	}
}
