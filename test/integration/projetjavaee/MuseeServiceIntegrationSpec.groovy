package projetjavaee



import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {

    Adresse uneAdresse
    Gestionnaire unGestionnaire
    MuseeService museeService
    //JeuTestService jeuTestService

    def setup() {

        uneAdresse =  new Adresse(numero:"20",rue: "jean jaures",codePostal: 31400,ville: "Toulouse" ).save(flush:true)
        unGestionnaire = new Gestionnaire(nom: "mairie").save(flush: true)

    }

    def cleanup() {
    }

    void "test la suppression d'un musee"() {

        given:"un musee existant en base"
        Musee unMusee = new Musee(nom:"Archive municipale",horaireOuverture: "de 8h à 18h",
                telephone:"0534768965",accesMetro:"metro A",accesBus: "bus 178")
        museeService.insertOrUpdateMusee(unMusee ,uneAdresse,unGestionnaire)

        when:"on déclenche la suppression du musee"
        museeService.deleteMusee(unMusee)

        then:"le musee est supprimée de la base"
        Musee.findById(unMusee.id) == null

        and:"ni l'adresse, ni le gestionnaire ne sont supprimés"
        Adresse.findById(uneAdresse.id) != null
        Gestionnaire.findById(unGestionnaire.id) != null

    }



    void "test insertion ou mise à jour d'un Musee"() {

        given:"un musee"
        Musee unMusee = new Musee(nom:"Archive municipale",horaireOuverture: "de 8h à 18h",
                telephone:"0534768965",accesMetro:"metro A",accesBus: "bus 178")

        and: "une adresse"
        uneAdresse =  new Adresse(numero:"20",rue: "jean jaures",codePostal: 31400,ville: "Toulouse" )

        and: "un gestionnaire"
        unGestionnaire = new Gestionnaire(nom: "mairie")

        when: "on tente de répercuter en base la création ou la modification du musee"
        Musee resMusee = museeService.insertOrUpdateMusee(unMusee,uneAdresse,unGestionnaire)

        then: "le musee resultant pointe sur le musee initale"
        resMusee == unMusee

        and:"le musee résultant n'a pas d'erreur"
        !resMusee.hasErrors()

        and:"le musee résultant a un id"
        resMusee.id

        and:"le musee est bien presente en base"
        Musee.findById(resMusee.id) != null

        and: "le musee a les bon attibut passé en paramètre"
        resMusee.adresse == uneAdresse
        resMusee.gestionnaire == unGestionnaire

    }

/*
    void "test du moteur de recherche sur les musees"() {

        given:"les musee, les adresses et les inscriptions fournis par le jeu de test "
        //jeuTestService

        when:"on cherche les musees dont le nom contient ... "
        List<Musee> resMusee = museeService.searchMusee("ct1",null,null)

        then:"on récupère uniquement les 2 inscriptions sur activité 1"
        resMusee.size() == 2
        resMusee*.id.contains(jeuTestService.jeanneOnAct1.id)
        resMusee*.id.contains(jeuTestService.jacquesOnAct1.id)

        when:"on cherche les musees dont le code postal est ... "
        resMusee = museeService.searchMusee(null,'Val',null)

        then:"on récupère uniquement l'inscription jacquesOnAct3"
        resMusee.size() == 1
        resMusee*.id.contains(jeuTestService.jacquesOnAct3.id)

        when:"on cherche les musee dont le nom de la rue est ... "
        resMusee = museeService.searchMusee(null,null,'Jacq')

        then:"on recupère les 2 inscriptions de Jacques"
        resMusee.size() == 2
        resMusee*.id.contains(jeuTestService.jacquesOnAct3.id)
        resMusee*.id.contains(jeuTestService.jacquesOnAct1.id)

        and:"elle sont ordonnées suivant le titre de l'activité"
        resMusee*.activite*.titre == [jeuTestService.activite1.titre, jeuTestService.activite3.titre]

        when:"on cherche les inscriptions sur lesquelles une personne dont le nom ou me prénom contient 'Jack'et dont les activités sont gérées par le responsable dont le nom ou le prenom contient 'Isa'  "
        resMusee = museeService.searchInscriptions(null,'Isa','Jacq')

        then:"on récupère uniquement l'inscription jacquesOnAct1"
        resMusee.size() == 1
        resMusee*.id.contains(jeuTestService.jacquesOnAct1.id)

        when:"on cherche les inscriptions dont le titre de l'activité contient 'Isa'"
        resMusee = museeService.searchInscriptions("Isa",null,null)

        then: "on ne récupère aucune inscription"
        resMusee.size() == 0

        when:"on positionne tous les critères à null"
        resMusee = museeService.searchInscriptions(null, null, null)

        then: "on récupère toutes les inscriptions"
        resMusee.size() == 3

        and:"elle sont ordonnées suivant le titre de l'activité"
        resMusee*.activite*.titre == [jeuTestService.activite1.titre, jeuTestService.activite1.titre, jeuTestService.activite3.titre]
    }
*/









}
