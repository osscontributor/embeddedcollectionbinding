package demo

class DemoController {

    def createEntry() {
        [entry: new Entry(params)]
    }
}
