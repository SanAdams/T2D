/* Sebastien Adam
*  CNT 4714 – Fall 2023 – Project Four
*  Assignment title: A Three-Tier Distributed Web-Based Application
*  Date: November 5, 2023
*/
package ServletsandHelpers;

public class QueryHelper {

    public QueryHelper() {

    }
    public String determineQueryType(String query) {
        String queryType = "";

        query = query.trim();
        String toks[] = query.split("\\s");
        
        switch(toks[0].toLowerCase().charAt(0)){
            case 's': queryType = "SELECT";
            break;
            case 'r':queryType = "REPLACE";
            break;
            case 'u':queryType = "UPDATE";
            break;
            case 'd':queryType = "DELETE";
            break;
            case 'i':queryType = "INSERT";
            break;
        }
        return queryType;
    }
}
