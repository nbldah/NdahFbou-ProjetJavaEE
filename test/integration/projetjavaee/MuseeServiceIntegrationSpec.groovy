package projetjavaee



import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {

    Adresse uneAdresse
    Gestionnaire unGestionnaire
    MuseeService museeService
   // JeuTestService jeuTestService

    def setup() {

        uneAdresse =  new Adresse(numero:"20",rue: "jean jaures",codePostal: "31400",ville: "Toulouse" ).save()
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
        uneAdresse =  new Adresse(numero:"20",rue: "jean jaures",codePostal: "31400",ville: "Toulouse" )

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


    void "test du moteur de recherche sur les musees"() {

        given:" une liste de musees "
        Musee.deleteAll()
  /*    Musee musee1 = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE",
                horaireOuverture: "Ouvert du lundi au vendredi de 9h à 17h",
                telephone: "0561616333", accesMetro: "Roseraie (A)",
                accesBus: "36, 38" ,
                adresse: new Adresse(numero: "4", rue: "RUE DES ARCHIVES", codePostal: "31400", ville: "Toulouse").save(),
                gestionnaire: new Gestionnaire(nom:"Mairie").save()).save()

        Musee musee2 = new Musee(nom: "ENSEMBLE CONVENTUEL DES JACOBINS",
                horaireOuverture: "Ouvert du lundi au vendredi de 9h à 17h",
                telephone: "0561616333", accesMetro: "Roseraie (A)",
                accesBus: "36, 38" ,
                adresse: new Adresse(numero: "4", rue: "RUE DU MAY", codePostal: "31000", ville: "Toulouse").save(),
                gestionnaire: new Gestionnaire(nom:"Mairie").save()).save()

        Musee musee3 = new Musee(nom: "MUSEE DU VIEUX TOULOUSE",
                horaireOuverture: "Ouvert du lundi au vendredi de 9h à 17h",
                telephone: "0561616333", accesMetro: "Roseraie (A)",
                accesBus: "36, 38" ,
                adresse: new Adresse(numero: "4", rue: "RUE DU JAPON", codePostal: "31400", ville: "Toulouse").save(),
                gestionnaire: new Gestionnaire(nom:"Mairie").save()).save()
*/

        when:"on cherche les musees dont le nom contient toulouse "
        List<Musee> resMusee = museeService.searchMusee('Toulouse',null,null)

        then:"on récupère uniquement les 2 musees "
        resMusee.size() == 2
       // resMusee*.id.contains(musee1.id)
       // resMusee*.id.contains(musee3.id)


        when:"on cherche les musees dont le code postal est 31000"
        resMusee = museeService.searchMusee(null,"31000",null)

        then:"on récupère uniquement le musee MUSEE ENSEMBLE CONVENTUEL DES JACOBINS"
        resMusee.size() == 1
        //resMusee.get(0).nom == "ENSEMBLE CONVENTUEL DES JACOBINS"

        when:"on cherche les musee dont le nom de la rue contient 'DU' "
        resMusee = museeService.searchMusee(null,null,'du')

        then:"on recupère les 2 musees"
        resMusee.size() == 2
        //resMusee*.id.contains(jeuTestService.musee2.id)
        //resMusee*.id.contains(jeuTestService.musee3.id)

        when:"on cherche les musee sur lesquelles nom contient 'MUSEE'ou le code postal est 31000"
        resMusee = museeService.searchMusee('MUSEE',"31000",null)

        then:"on récupère les 2 musees "
        resMusee.size() == 2
        //resMusee*.id.contains(jeuTestService.musee2.id)
        //resMusee*.id.contains(jeuTestService.musee3.id)

        when:"on cherche les musees dont le nom de l'activité contient 'zzz' "
        resMusee = museeService.searchMusee("zzz",null,null)

        then: "on ne récupère aucune inscription"
        resMusee.size() == 0

        when:"on positionne tous les critères à null"
        resMusee = museeService.searchMusee(null, null, null)

        then: "on récupère toutes les inscriptions"
        resMusee.size() == 3

    }










}
