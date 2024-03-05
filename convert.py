import csv
import json

def csv_to_json(csv_file_path, json_file_path):
    # read file
    with open(csv_file_path, 'r') as csv_file:
        csv_reader = csv.DictReader(csv_file)
        
        # convert data to a list of dictionaries
        data = [row for row in csv_reader]

    # write json file using data
    with open(json_file_path, 'w') as json_file:
        json.dump(data, json_file, indent=2)

# usage
csv_to_json('Pothole_2022.csv', 'Pothole_2022.json')