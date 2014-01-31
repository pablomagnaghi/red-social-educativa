package com.fiuba



import grails.test.mixin.*
import spock.lang.*

@TestFor(MaterialActividadController)
@Mock(MaterialActividad)
class MaterialActividadControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.materialActividadInstanceList
            model.materialActividadInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.materialActividadInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def materialActividad = new MaterialActividad()
            materialActividad.validate()
            controller.save(materialActividad)

        then:"The create view is rendered again with the correct model"
            model.materialActividadInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            materialActividad = new MaterialActividad(params)

            controller.save(materialActividad)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/materialActividad/show/1'
            controller.flash.message != null
            MaterialActividad.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def materialActividad = new MaterialActividad(params)
            controller.show(materialActividad)

        then:"A model is populated containing the domain instance"
            model.materialActividadInstance == materialActividad
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def materialActividad = new MaterialActividad(params)
            controller.edit(materialActividad)

        then:"A model is populated containing the domain instance"
            model.materialActividadInstance == materialActividad
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/materialActividad/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def materialActividad = new MaterialActividad()
            materialActividad.validate()
            controller.update(materialActividad)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.materialActividadInstance == materialActividad

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            materialActividad = new MaterialActividad(params).save(flush: true)
            controller.update(materialActividad)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/materialActividad/show/$materialActividad.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/materialActividad/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def materialActividad = new MaterialActividad(params).save(flush: true)

        then:"It exists"
            MaterialActividad.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(materialActividad)

        then:"The instance is deleted"
            MaterialActividad.count() == 0
            response.redirectedUrl == '/materialActividad/index'
            flash.message != null
    }
}
