__This is a UI automation testing that writing with Selenium.__

<br>

## Used Technologies

- Java
- Selenium
- TestNg

# Scenarios
- Visit https://useinsider.com/ and check Insider home page is opened or not 
- Select the “Company” menu in the navigation bar, select “Careers” and check Career
   page, its Locations, Teams, and Life at Insider blocks are open or not 
- Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter
   jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check the
   presence of the job list
- Check that all jobs’ Position contains “Quality Assurance”, Department contains
   “Quality Assurance”, and Location contains “Istanbul, Turkey”
- Click the “View Role” button and check that this action redirects us to the Lever
   Application form pag


## Running The Tests
- Clone the project and open Test Automation-API project in EbubekirAycibin file :

```sh
git clone https://github.com/bekiraycibin/EbubekirAycibin.git
```
- install dependencies in pom.xml.
- Use ```mvn test -DsuiteXmlFile=testng.xml``` code in terminal or click the run button in .xml file on the ide to run the test.
  <br>