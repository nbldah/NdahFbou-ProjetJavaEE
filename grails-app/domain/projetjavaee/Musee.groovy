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
        telephone maxSize: 10 , nullable:false , blank: false
        accesMetro nullable:false, blank: false
        accesBus nullable:false, blank: false
        adresse nullable: false
        gestionnaire nullable: false
        //([0-9]+[ -_/])*[0-9]+       pour le telephone


    }

}
