package projetjavaee

class DemandeVisite {
    String code
    Date dateDebutPeriode
    Date dateFinPeriode
    int nbPersonnes
    String statut

    static constraints = {
        code blank: false, nullable: true
        dateDebutPeriode blank: false, nullable: false
        dateFinPeriode blank: false, nullable: false
        nbPersonnes range: 1..6, nullable: false
        statut blank: false, inList: ["En cours de traitement", "Confirmee", "Refusee"], nullable: true
    }
}
