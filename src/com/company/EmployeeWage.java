package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeWage implements IComputeWage{
    //constant
    public static final int isPartTime=0;
    public static final int isFullTime=1;

    private ArrayList<CompanyEmpWage> empList;
    private Map<String,CompanyEmpWage> empWageMap;
    public EmployeeWage() {
        empList=new ArrayList<>();
        empWageMap=new HashMap<>();
    }
    @Override
    public void addCompanyEmpWage(String company, int empRatePerHour, int numOfWorkingDays, int maximumHoursPerMonth) {
        CompanyEmpWage empWage=new CompanyEmpWage(company,empRatePerHour,numOfWorkingDays,maximumHoursPerMonth);
        empList.add(empWage);
        empWageMap.put(company,empWage);
    }

    @Override
    public void computeEmpWage() {
        for(int i=0;i<empList.size();i++) {
            empList.get(i).setTotalEmpWage(this.computeEmpWage(empList.get(i)));
            System.out.println(empList.get(i));
        }

    }
    @Override
    public int getTotalWage(String company) {
        return empWageMap.get(company).totalWage;
    }

    private  int computeEmpWage(CompanyEmpWage companyEmpWage) {
        int empHours=0, totalEmpHours=0, totalWorkingDays=0;
        while(totalEmpHours<=companyEmpWage.maximumHoursPerMonth && totalWorkingDays<companyEmpWage.numOfWorkingDays){
            int check=(int)(Math.floor(Math.random()*10)%2);
            switch(check){
                case isPartTime:
                    empHours=4;
                    break;
                case isFullTime:
                    empHours=8;
                    break;
                default :
                    empHours=0;
            }
            totalEmpHours+=empHours;
            totalWorkingDays+=1;
        }
        return (totalEmpHours*companyEmpWage.empRatePerHour);

    }
    public static void main(String args[]) {
        EmployeeWage employeeWage=new EmployeeWage();
        employeeWage.addCompanyEmpWage("Dmart", 15, 21, 80);
        employeeWage.addCompanyEmpWage("Reliance", 15, 18, 100);
        employeeWage.computeEmpWage();
        System.out.println("Total wage for Reliance company: "+employeeWage.getTotalWage("Reliance"));
    }

}
