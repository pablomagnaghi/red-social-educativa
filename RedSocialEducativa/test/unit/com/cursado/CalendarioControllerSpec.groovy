package com.cursado



import grails.test.mixin.*
import spock.lang.*

@TestFor(CalendarioController)
@Mock(Calendario)
class CalendarioControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.calendarioInstanceList
            model.calendarioInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.crear()

        then:"The model is correctly created"
            model.calendarioInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def calendario = new Calendario()
            calendario.validate()
            controller.save(calendario)

        then:"The create view is rendered again with the correct model"
            model.calendarioInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            calendario = new Calendario(params)

            controller.save(calendario)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/calendario/show/1'
            controller.flash.message != null
            Calendario.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.mostrar(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def calendario = new Calendario(params)
            controller.mostrar(calendario)

        then:"A model is populated containing the domain instance"
            model.calendarioInstance == calendario
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.editar(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def calendario = new Calendario(params)
            controller.editar(calendario)

        then:"A model is populated containing the domain instance"
            model.calendarioInstance == calendario
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/calendario/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def calendario = new Calendario()
            calendario.validate()
            controller.update(calendario)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.calendarioInstance == calendario

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            calendario = new Calendario(params).save(flush: true)
            controller.update(calendario)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/calendario/show/$calendario.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/calendario/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def calendario = new Calendario(params).save(flush: true)

        then:"It exists"
            Calendario.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(calendario)

        then:"The instance is deleted"
            Calendario.count() == 0
            response.redirectedUrl == '/calendario/index'
            flash.message != null
    }
}
