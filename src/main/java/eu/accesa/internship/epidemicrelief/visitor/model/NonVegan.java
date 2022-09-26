package eu.accesa.internship.epidemicrelief.visitor.model;

import eu.accesa.internship.epidemicrelief.visitor.Visitor;

import java.util.List;

public class NonVegan extends HouseholdMember {


    public NonVegan(Long numberOfPersons) {
        super(numberOfPersons);
    }

    public Long getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Long numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public List<ProductNecessity> productNecessityList(Visitor visitor) {
        return visitor.visit(this);

    }
}
