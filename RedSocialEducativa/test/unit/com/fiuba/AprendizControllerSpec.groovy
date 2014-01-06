package com.fiuba



import grails.test.mixin.*
import spock.lang.*

@TestFor(AprendizController)
@Mock(Aprendiz)
class AprendizControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.aprendizInstanceList
            model.aprendizInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.aprendizInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def aprendiz = new Aprendiz()
            aprendiz.validate()
            controller.save(aprendiz)

        then:"The create view is rendered again with the correct model"
            model.aprendizInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            aprendiz = new Aprendiz(params)

            controller.save(aprendiz)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/aprendiz/show/1'
            controller.flash.message != null
            Aprendiz.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def aprendiz = new Aprendiz(params)
            controller.show(aprendiz)

        then:"A model is populated containing the domain instance"
            model.aprendizInstance == aprendiz
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def aprendiz = new Aprendiz(params)
            controller.edit(aprendiz)

        then:"A model is populated containing the domain instance"
            model.aprendizInstance == aprendiz
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/aprendiz/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def aprendiz = new Aprendiz()
            aprendiz.validate()
            controller.update(aprendiz)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.aprendizInstance == aprendiz

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            aprendiz = new Aprendiz(params).save(flush: true)
            controller.update(aprendiz)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/aprendiz/show/$aprendiz.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/aprendiz/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def aprendiz = new Aprendiz(params).save(flush: true)

        then:"It exists"
            Aprendiz.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(aprendiz)

        then:"The instance is deleted"
            Aprendiz.count() == 0
            response.redirectedUrl == '/aprendiz/index'
            flash.message != null
    }
}
