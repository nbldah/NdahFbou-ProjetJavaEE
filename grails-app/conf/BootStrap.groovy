import projetjavaee.JeuTestService

class BootStrap {

    JeuTestService jeuTestService

    def init = { servletContext ->
        jeuTestService.createJeuTestAdresse()
        jeuTestService.createJeuTestGestionnaire()
        jeuTestService.createJeuTestMusee()
    }
    def destroy = {
    }
}
