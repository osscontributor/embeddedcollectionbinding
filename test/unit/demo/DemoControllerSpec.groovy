package demo

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemoController)
@Mock([Entry, Sale, ProductLineItem, Product])
class DemoControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        given:
        def product = new Product(name: 'initial product name',
                                  description: 'initial product description',
                                  code: 'initial product code').save()

        when:
        params.'sale.description' = 'some sale'
        params.'sale.productLineItems[0].product.id' = product.id
        params.'sale.productLineItems[0].name' = 'updated product name'
        params.'sale.productLineItems[0].description' = 'updated product description'
        def model = controller.createEntry()
        def entry = model.entry

        then:
        entry
        entry.sale
        entry.sale.description == 'some sale'
        entry.sale.productLineItems[0] instanceof ProductLineItem
        entry.sale.productLineItems[0].name == 'updated product name'
        entry.sale.productLineItems[0].description == 'updated product description'
        entry.sale.productLineItems[0].product
        entry.sale.productLineItems[0].product.code == 'initial product code'
    }
}
