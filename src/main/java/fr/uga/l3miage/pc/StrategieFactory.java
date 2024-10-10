package fr.uga.l3miage.pc;

public abstract class StrategieFactory {
    abstract Strategie createStrategie();
}


class StrategieAleatoireFactory extends StrategieFactory{
    @Override
    Strategie createStrategie() {
        return new StrategieAleatoire();
    }
}

class StrategieToujoursTrahirFactory extends StrategieFactory{
    @Override
    Strategie createStrategie() {
        return new StrategieToujoursTrahir();
    }
}


class StrategieToujoursCoopererFactory extends StrategieFactory{
    @Override
    Strategie createStrategie() {
        return new StrategieToujoursCooperer();
    }
}

class StrategieDonnantDonnantFactory extends StrategieFactory{
    @Override
    Strategie createStrategie() {
        return new StrategieDonnantDonnant();
    }
}

class StrategieRancunierFactory extends StrategieFactory{
    @Override
    Strategie createStrategie() {
        return new StrategieRancunier();
    }
}


