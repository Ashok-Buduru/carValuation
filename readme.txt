
Pre-requisite:
1. Read all input from car_input.txt file
2. Search car registration extracted from an Input file onto https://cartaxcheck.co.uk/ site
3. Compare web search results with an external car_output.txt

Scenarios:
1. Run tests using testng.xml and check the matched and unmatched records in car_results_matched and car_result_unmatched files
2. Change matched car registration details in car_Output.txt file and check console shows correct failed detail (Reg/Model/Color/Year)
3. If car registration number from an input file is not available in Output file, a proper message is displayed in console logs
4. Console logs/Test run configurations are shown correctly for all failed scenarios.

Observations:
1. Test will continue searching for all registration number and update the status in correct status file (matched/unmatched file) and in console logs
2. Placed an assertion to verify actual and expected results are matched using data provider. Tests will run for all car registration numbers from an car_input.txt file.
3. If registration number in car_input.txt file doesn't match with car_output.txt file, straightaway fail test scenario because condition is not met for comparison.
4. We can add/update further input.txt and/or Output.txt files in future and run the tests.
