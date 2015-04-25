package projetjavaee


import spock.lang.*

/**
 *
 */
class JeuTestServiceIntegrationSpec extends Specification {


    JeuTestService jeuTestService


    void "test creation jeu de tests pour musees"() {

        given: "une base ne contenant pas de musee"
        Musee.count() == 0

        when: "on crée le jeu de test pour les musee"
        jeuTestService.createJeuTestAdresse()
        jeuTestService.createJeuTestGestionnaire()
        jeuTestService.createJeuTestMusee()

        then: "12 nouveaux musees ont été crées en base"
        Musee.count() == 12


        when:" des musee exitent deja dans la base"
        Musee.count() == 12

        and:"on déclenche à nouveau la création du jeu de test pour musees"
        jeuTestService.createJeuTestAdresse()
        jeuTestService.createJeuTestGestionnaire()
        jeuTestService.createJeuTestMusee()

        then:"aucun nouveau musee n'est crée"
        Musee.count() == 12

    }

}
