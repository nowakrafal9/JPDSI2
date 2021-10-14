package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped

public class CreditBB {
	private String amount = "420000";
	private String years = "3";
	private Integer percent = 5;
	private Integer instPerYear = 2;
	private String installment;

	@Inject
	FacesContext ctx;

	// Get, set kwota kredytu
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	// Get, set lata kredytu
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}

	// Get, set oprocentowanie kredytu
	public Integer getPercent() {
		return percent;
	}
	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	// Get, set iloœæ rat rocznie
	public Integer getInstPerYear() {
		return instPerYear;
	}
	public void setInstPerYear(Integer instPerYear) {
		this.instPerYear = instPerYear;
	}
	
	// Get, set rata kredytu
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}
	
	// Wyliczenie wysokoœci raty
	public boolean doMath() {
		try {
			double a = Double.parseDouble(this.amount);
			double y = Double.parseDouble(this.years);
			double r = this.percent;
			double k = this.instPerYear;
			double result;
			
			r /= 100;
			result = (a*r)/(k*(1-Math.pow((k/(k+r)),(y*k))));
			
			installment = String.format("%.2f", result);
			
			return true;
		} catch(Exception ex) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
			return false;
		}
	}
	
	// Wyœwietl wynik na nowej stronie
	public String calc() {
		if(doMath()) { return "showresult"; }
		return null;
	}
	
	// Wyœwietl wynik przy pomocy AJAX
	public String calc_AJAX() {
		if (doMath()) { ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wysokoœæ raty: " + installment, null)); }
		return null;
	}
}
