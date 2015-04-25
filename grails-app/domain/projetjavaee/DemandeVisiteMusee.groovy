package projetjavaee

class DemandeVisiteMusee {
    Date dateDemande
    static  belongsTo =[musee : Musee, demandeVisite: DemandeVisite]

    static constraints = {
        dateDemande: nullable: false

    }
}
