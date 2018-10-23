import groovy.json.JsonOutput
import org.apache.commons.io.FileUtils
import org.bbop.inca.model.VG16CatDogModel
import org.bbop.inca.predictor.CatDogPredictor
import org.bbop.inca.predictor.Predictor

/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */

class IndividualFigureDetectApp {

    static String evaluateFigures(){

//        File file = new VG16CatDogModel().downloadModel()

        String[] extensions = ["tif"]
        File imageResources = new File("resources/figsharesingle")
        File[] files = FileUtils.listFiles(imageResources, extensions, false)
        Predictor predictor = new CatDogPredictor()


        def resultList = []
        for (f in files) {
            def petType = predictor.predict(f, 0.5)
            println "${f.absolutePath}  -> ${petType.toString()}"
            def result = [:]
            result.put("path",f.absolutePath)
            result.put("name",f.name)
            result.put("result",petType.toString())
            resultList.add(result)
        }
//        println JsonOutput.prettyPrint(JsonOutput.toJson(resultList))
        File outputFile = new File("resources/figsharesingle/outputFile.json")
        outputFile.delete()
        outputFile.text = JsonOutput.toJson(resultList).toString()

        return outputFile.text
    }

    static void main(String[] args) {
        evaluateFigures()
    }

}