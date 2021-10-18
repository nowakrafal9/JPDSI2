package calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped

public class CreditBB {
	private Integer amount;
	private Integer years;
	private Double percent = 1.0;
	private Integer instPerYear;
	private String installment;
	
	@Inject
	FacesContext ctx;

	// Get, set kwota kredytu
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	// Get, set lata kredytu
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}

	// Get, set oprocentowanie kredytu
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
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

	// Wyliczenie wysokoœci raty
	public boolean doMath() {
			double a = this.amount;
			double y = this.years;
			double r = this.percent;
			double k = this.instPerYear;
			double result;
			
			r /= 100;
			result = (a*r)/(k*(1-Math.pow((k/(k+r)),(y*k))));
			
			installment = String.format("%.2f", result);
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obliczenia wykonane poprawnie", null));
			return true;	
	}
	
	// Wyœwietl wynik na nowej stronie
	public String calc() {
		if(doMath()) { return "showresult"; }
		return null;
	}
	
	// Wyœwietl wynik przy pomocy AJAX
	public String calc_AJAX() {
		if (doMath()) { ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wysokoœæ raty: " + installment + " z³.", null)); }
		return null;
	}
}
