package com.encuesta



import grails.test.mixin.*
import spock.lang.*

@TestFor(PreguntaDesarrolloController)
@Mock(PreguntaDesarrollo)
class PreguntaDesarrolloControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.preguntaDesarrolloInstanceList
            model.preguntaDesarrolloInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.preguntaDesarrolloInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def preguntaDesarrollo = new PreguntaDesarrollo()
            preguntaDesarrollo.validate()
            controller.save(preguntaDesarrollo)

        then:"The create view is rendered again with the correct model"
            model.preguntaDesarrolloInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            preguntaDesarrollo = new PreguntaDesarrollo(params)

            controller.save(preguntaDesarrollo)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/preguntaDesarrollo/show/1'
            controller.flash.message != null
            PreguntaDesarrollo.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def preguntaDesarrollo = new PreguntaDesarrollo(params)
            controller.show(preguntaDesarrollo)

        then:"A model is populated containing the domain instance"
            model.preguntaDesarrolloInstance == preguntaDesarrollo
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def preguntaDesarrollo = new PreguntaDesarrollo(params)
            controller.edit(preguntaDesarrollo)

        then:"A model is populated containing the domain instance"
            model.preguntaDesarrolloInstance == preguntaDesarrollo
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/preguntaDesarrollo/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def preguntaDesarrollo = new PreguntaDesarrollo()
            preguntaDesarrollo.validate()
            controller.update(preguntaDesarrollo)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.preguntaDesarrolloInstance == preguntaDesarrollo

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            preguntaDesarrollo = new PreguntaDesarrollo(params).save(flush: true)
            controller.update(preguntaDesarrollo)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/preguntaDesarrollo/show/$preguntaDesarrollo.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/preguntaDesarrollo/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def preguntaDesarrollo = new PreguntaDesarrollo(params).save(flush: true)

        then:"It exists"
            PreguntaDesarrollo.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(preguntaDesarrollo)

        then:"The instance is deleted"
            PreguntaDesarrollo.count() == 0
            response.redirectedUrl == '/preguntaDesarrollo/index'
            flash.message != null
    }
}
