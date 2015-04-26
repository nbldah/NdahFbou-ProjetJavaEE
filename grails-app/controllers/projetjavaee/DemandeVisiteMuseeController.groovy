package projetjavaee


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteMuseeController {

    DemandeVisiteMuseeService demandeVisiteMuseeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DemandeVisiteMusee.list(params), model: [demandeVisiteMuseeInstanceCount: DemandeVisiteMusee.count()]
    }

    def show(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        respond demandeVisiteMuseeInstance
    }

    def create() {
        respond new DemandeVisiteMusee(params)
    }

    def renderDemandeVisite() {
        render(view: "index")
    }

    def redirecToMusee() {
        redirect(controller: "musee", action: "renderMusee")
    }

    def doValiderDemande() {
        Musee m = Musee.findByNom(params.musee)

        DemandeVisite dv = new DemandeVisite(code: (new Date()).time.toString(), dateDebutPeriode: params.dateDebut, dateFinPeriode: params.dateFin, nbPersonnes: params.nbPers, statut: "En cours de traitement")
        DemandeVisiteMusee dvm = new DemandeVisiteMusee(musee: m, demandeVisite: dv)
        def demandeVisiteMusee = demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMusee(dvm, m, dv)

        session.setAttribute("etatDemande", true)
        session.setAttribute("code", dvm.demandeVisite.code)

        render(view: 'index', model: [demandeVisiteMusee: demandeVisiteMusee])
    }

    @Transactional
    def save(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteMuseeInstance.hasErrors()) {
            respond demandeVisiteMuseeInstance.errors, view: 'create'
            return
        }

        demandeVisiteMuseeInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect demandeVisiteMuseeInstance
            }
            '*' { respond demandeVisiteMuseeInstance, [status: CREATED] }
        }
    }

    def edit(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        respond demandeVisiteMuseeInstance
    }

    @Transactional
    def update(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteMuseeInstance.hasErrors()) {
            respond demandeVisiteMuseeInstance.errors, view: 'edit'
            return
        }

        demandeVisiteMuseeInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DemandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect demandeVisiteMuseeInstance
            }
            '*' { respond demandeVisiteMuseeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DemandeVisiteMusee demandeVisiteMuseeInstance) {

        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        demandeVisiteMuseeInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DemandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
