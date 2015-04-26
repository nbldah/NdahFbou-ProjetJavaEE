package projetjavaee

class Gestionnaire {
    String nom

    static constraints = {
        nom blank: false, nullable: false
    }

    public String toString() {
        "${nom}"
    }
}
