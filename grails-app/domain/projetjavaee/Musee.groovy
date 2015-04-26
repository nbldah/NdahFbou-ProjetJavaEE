package projetjavaee

class Musee {

    String nom
    String horaireOuverture
    String telephone
    String accesMetro
    String accesBus

    static belongsTo = [
            gestionnaire: Gestionnaire,
            adresse     : Adresse
    ]

    static constraints = {
        nom nullable: false, blank: false
        horaireOuverture nullable: false , blank : false
        telephone  nullable:false , blank: false
        accesMetro nullable:false, blank: false
        accesBus nullable:false, blank: false
        //([0-9]+[ -_/])*[0-9]+       pour le telephone

    }


    @Override
    public String toString() {
        id
    }
}
