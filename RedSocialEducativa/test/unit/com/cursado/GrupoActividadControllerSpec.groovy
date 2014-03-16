package com.cursado



import grails.test.mixin.*
import spock.lang.*

@TestFor(GrupoActividadController)
@Mock(GrupoActividad)
class GrupoActividadControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.grupoActividadInstanceList
            model.grupoActividadInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.crear()

        then:"The model is correctly created"
            model.grupoActividadInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def grupoActividad = new GrupoActividad()
            grupoActividad.validate()
            controller.save(grupoActividad)

        then:"The create view is rendered again with the correct model"
            model.grupoActividadInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            grupoActividad = new GrupoActividad(params)

            controller.save(grupoActividad)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/grupoActividad/show/1'
            controller.flash.message != null
            GrupoActividad.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.mostrar(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def grupoActividad = new GrupoActividad(params)
            controller.mostrar(grupoActividad)

        then:"A model is populated containing the domain instance"
            model.grupoActividadInstance == grupoActividad
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.editar(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def grupoActividad = new GrupoActividad(params)
            controller.editar(grupoActividad)

        then:"A model is populated containing the domain instance"
            model.grupoActividadInstance == grupoActividad
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/grupoActividad/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def grupoActividad = new GrupoActividad()
            grupoActividad.validate()
            controller.update(grupoActividad)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.grupoActividadInstance == grupoActividad

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            grupoActividad = new GrupoActividad(params).save(flush: true)
            controller.update(grupoActividad)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/grupoActividad/show/$grupoActividad.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/grupoActividad/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def grupoActividad = new GrupoActividad(params).save(flush: true)

        then:"It exists"
            GrupoActividad.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(grupoActividad)

        then:"The instance is deleted"
            GrupoActividad.count() == 0
            response.redirectedUrl == '/grupoActividad/index'
            flash.message != null
    }
}
