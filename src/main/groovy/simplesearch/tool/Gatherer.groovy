package simplesearch.tool

import groovy.json.JsonOutput

class Gatherer {
    
    static def url = "https://www.imdb.com/list/ls050782187/?sort=user_rating,desc&st_dt=&mode=detail&page="
    
    static void main(String[] args) {
        
        def records = []
        
        (1..5).each { i ->
            def conn = new URL(url+"$i").openConnection()
            conn.setRequestProperty('Accept-Language', 'en;q=0.8, pl;q=0.2')
            conn.setRequestProperty('Accept-Charset', 'UTF-8')
            def content = conn.getInputStream().getText()

            def res = content =~ /(?s)lister-item-index.*?>(\d+)\.<.*?title.*?>(.+?)<.*?Stars:(.*?)<\/p>/
            if (res.findAll()) {
                res.each { mat ->
                    def staff = mat[3]
                    staff = staff.replaceAll('(?s)<.*?>', '').replaceAll('\n', '').trim().toLowerCase()
                    records.add([title: mat[2], rank: mat[1], staff: [], '_class': 'simplesearch.model.Movie'])
                    records.last().staff = staff.split(', ')
                }
            }
        }
        
        File file = new File("src/main/resources/db-input.json")
        def json = JsonOutput.toJson(records)
        file.write JsonOutput.prettyPrint(json)
        
        print 'DONE'
    }
}

