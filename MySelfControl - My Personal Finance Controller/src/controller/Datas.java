package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.Months;


public class Datas {
	final static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	final static SimpleDateFormat formatEn = new SimpleDateFormat("yyyy-MM-dd");
	private DateTime startDate;
	
	
	public Datas(String date) {
		try {
			startDate = new DateTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static String returnFormattedDate(String date) {
		try {
			return format.format(formatEn.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int calcFinalDate(int parcelas) {
		DateTime finalDate = startDate.plusMonths(parcelas + 1);
		return Months.monthsBetween(new DateTime(),finalDate).getMonths();
	}
}
