package file;

public class Time {
	private final int END_TIME = 365;
	private int days, hours, minutes;
	private boolean gameover = false;

	public Time() {
		days = 1;
		hours = 6;
		minutes = 30;
	}

	public String getTime() {
		if (hours <= 12)
			return hours + ":" + minutes + " am";
		return hours - 12 + ":" + minutes + " pm";
	}

	public int getDay() {
		return days;
	}

	private void updateTime() {
		while (minutes > 60) {
			minutes -= 60;
			hours++;
		}
		while (hours > 24) {
			hours -= 24;
			days++;
		}
		if (days >= END_TIME) {
			gameover = true;
		}
	}

	public boolean isGameover() {
		return gameover;
	}

	public void addTime(int hours) {
		this.hours += hours;
		updateTime();
	}
	
	public void addTime(int hours, int minutes) {
		this.hours += hours;
		this.minutes += minutes;
		updateTime();
	}
}
