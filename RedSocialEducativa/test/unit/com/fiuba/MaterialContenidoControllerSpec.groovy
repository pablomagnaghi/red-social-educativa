package com.fiuba



import grails.test.mixin.*
import spock.lang.*

@TestFor(MaterialContenidoController)
@Mock(MaterialContenido)
class MaterialContenidoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.materialContenidoInstanceList
            model.materialContenidoInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.materialContenidoInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def materialContenido = new MaterialContenido()
            materialContenido.validate()
            controller.save(materialContenido)

        then:"The create view is rendered again with the correct model"
            model.materialContenidoInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            materialContenido = new MaterialContenido(params)

            controller.save(materialContenido)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/materialContenido/show/1'
            controller.flash.message != null
            MaterialContenido.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def materialContenido = new MaterialContenido(params)
            controller.show(materialContenido)

        then:"A model is populated containing the domain instance"
            model.materialContenidoInstance == materialContenido
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def materialContenido = new MaterialContenido(params)
            controller.edit(materialContenido)

        then:"A model is populated containing the domain instance"
            model.materialContenidoInstance == materialContenido
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/materialContenido/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def materialContenido = new MaterialContenido()
            materialContenido.validate()
            controller.update(materialContenido)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.materialContenidoInstance == materialContenido

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            materialContenido = new MaterialContenido(params).save(flush: true)
            controller.update(materialContenido)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/materialContenido/show/$materialContenido.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/materialContenido/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def materialContenido = new MaterialContenido(params).save(flush: true)

        then:"It exists"
            MaterialContenido.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(materialContenido)

        then:"The instance is deleted"
            MaterialContenido.count() == 0
            response.redirectedUrl == '/materialContenido/index'
            flash.message != null
    }
}
