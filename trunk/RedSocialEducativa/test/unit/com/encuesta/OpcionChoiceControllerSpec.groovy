package com.encuesta



import grails.test.mixin.*
import spock.lang.*

@TestFor(OpcionChoiceController)
@Mock(OpcionChoice)
class OpcionChoiceControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.opcionChoiceInstanceList
            model.opcionChoiceInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.opcionChoiceInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def opcionChoice = new OpcionChoice()
            opcionChoice.validate()
            controller.save(opcionChoice)

        then:"The create view is rendered again with the correct model"
            model.opcionChoiceInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            opcionChoice = new OpcionChoice(params)

            controller.save(opcionChoice)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/opcionChoice/show/1'
            controller.flash.message != null
            OpcionChoice.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def opcionChoice = new OpcionChoice(params)
            controller.show(opcionChoice)

        then:"A model is populated containing the domain instance"
            model.opcionChoiceInstance == opcionChoice
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def opcionChoice = new OpcionChoice(params)
            controller.edit(opcionChoice)

        then:"A model is populated containing the domain instance"
            model.opcionChoiceInstance == opcionChoice
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/opcionChoice/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def opcionChoice = new OpcionChoice()
            opcionChoice.validate()
            controller.update(opcionChoice)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.opcionChoiceInstance == opcionChoice

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            opcionChoice = new OpcionChoice(params).save(flush: true)
            controller.update(opcionChoice)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/opcionChoice/show/$opcionChoice.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/opcionChoice/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def opcionChoice = new OpcionChoice(params).save(flush: true)

        then:"It exists"
            OpcionChoice.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(opcionChoice)

        then:"The instance is deleted"
            OpcionChoice.count() == 0
            response.redirectedUrl == '/opcionChoice/index'
            flash.message != null
    }
}
