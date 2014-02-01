package com.fiuba



import grails.test.mixin.*
import spock.lang.*

@TestFor(TemaActividadController)
@Mock(TemaActividad)
class TemaActividadControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.temaActividadInstanceList
            model.temaActividadInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.temaActividadInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def temaActividad = new TemaActividad()
            temaActividad.validate()
            controller.save(temaActividad)

        then:"The create view is rendered again with the correct model"
            model.temaActividadInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            temaActividad = new TemaActividad(params)

            controller.save(temaActividad)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/temaActividad/show/1'
            controller.flash.message != null
            TemaActividad.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def temaActividad = new TemaActividad(params)
            controller.show(temaActividad)

        then:"A model is populated containing the domain instance"
            model.temaActividadInstance == temaActividad
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def temaActividad = new TemaActividad(params)
            controller.edit(temaActividad)

        then:"A model is populated containing the domain instance"
            model.temaActividadInstance == temaActividad
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/temaActividad/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def temaActividad = new TemaActividad()
            temaActividad.validate()
            controller.update(temaActividad)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.temaActividadInstance == temaActividad

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            temaActividad = new TemaActividad(params).save(flush: true)
            controller.update(temaActividad)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/temaActividad/show/$temaActividad.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/temaActividad/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def temaActividad = new TemaActividad(params).save(flush: true)

        then:"It exists"
            TemaActividad.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(temaActividad)

        then:"The instance is deleted"
            TemaActividad.count() == 0
            response.redirectedUrl == '/temaActividad/index'
            flash.message != null
    }
}
