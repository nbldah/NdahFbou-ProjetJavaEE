package projetjavaee

class Adresse {

    String numero
    String rue
    String codePostal
    String ville


    static constraints = {

        numero nullable:false, blank:false
        rue nullable: false ,blank:false
        codePostal inList: ["31000","31200","31300","31400","31500"]
        ville nullable: false ,blank:false ,inList:['Toulouse']

    }
}
