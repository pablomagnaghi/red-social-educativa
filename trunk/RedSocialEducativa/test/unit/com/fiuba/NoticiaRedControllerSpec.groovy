package com.fiuba



import grails.test.mixin.*
import spock.lang.*

@TestFor(NoticiaRedController)
@Mock(NoticiaRed)
class NoticiaRedControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.noticiaRedInstanceList
            model.noticiaRedInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.noticiaRedInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def noticiaRed = new NoticiaRed()
            noticiaRed.validate()
            controller.save(noticiaRed)

        then:"The create view is rendered again with the correct model"
            model.noticiaRedInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            noticiaRed = new NoticiaRed(params)

            controller.save(noticiaRed)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/noticiaRed/show/1'
            controller.flash.message != null
            NoticiaRed.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def noticiaRed = new NoticiaRed(params)
            controller.show(noticiaRed)

        then:"A model is populated containing the domain instance"
            model.noticiaRedInstance == noticiaRed
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def noticiaRed = new NoticiaRed(params)
            controller.edit(noticiaRed)

        then:"A model is populated containing the domain instance"
            model.noticiaRedInstance == noticiaRed
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/noticiaRed/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def noticiaRed = new NoticiaRed()
            noticiaRed.validate()
            controller.update(noticiaRed)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.noticiaRedInstance == noticiaRed

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            noticiaRed = new NoticiaRed(params).save(flush: true)
            controller.update(noticiaRed)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/noticiaRed/show/$noticiaRed.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/noticiaRed/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def noticiaRed = new NoticiaRed(params).save(flush: true)

        then:"It exists"
            NoticiaRed.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(noticiaRed)

        then:"The instance is deleted"
            NoticiaRed.count() == 0
            response.redirectedUrl == '/noticiaRed/index'
            flash.message != null
    }
}
