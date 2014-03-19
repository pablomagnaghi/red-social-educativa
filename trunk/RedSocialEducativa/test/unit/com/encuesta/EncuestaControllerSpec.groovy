package com.encuesta



import grails.test.mixin.*
import spock.lang.*

@TestFor(EncuestaController)
@Mock(Encuesta)
class EncuestaControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.encuestaInstanceList
            model.encuestaInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.encuestaInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def encuesta = new Encuesta()
            encuesta.validate()
            controller.save(encuesta)

        then:"The create view is rendered again with the correct model"
            model.encuestaInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            encuesta = new Encuesta(params)

            controller.save(encuesta)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/encuesta/show/1'
            controller.flash.message != null
            Encuesta.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def encuesta = new Encuesta(params)
            controller.show(encuesta)

        then:"A model is populated containing the domain instance"
            model.encuestaInstance == encuesta
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def encuesta = new Encuesta(params)
            controller.edit(encuesta)

        then:"A model is populated containing the domain instance"
            model.encuestaInstance == encuesta
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/encuesta/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def encuesta = new Encuesta()
            encuesta.validate()
            controller.update(encuesta)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.encuestaInstance == encuesta

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            encuesta = new Encuesta(params).save(flush: true)
            controller.update(encuesta)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/encuesta/show/$encuesta.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/encuesta/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def encuesta = new Encuesta(params).save(flush: true)

        then:"It exists"
            Encuesta.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(encuesta)

        then:"The instance is deleted"
            Encuesta.count() == 0
            response.redirectedUrl == '/encuesta/index'
            flash.message != null
    }
}
