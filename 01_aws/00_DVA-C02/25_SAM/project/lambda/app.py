import json

def lambda_handler(event, context):
    """A simple Lambda function that returns a greeting"""
    return {
        "statusCode": 200,
        "body": json.dumps({
            "message": "Hello, World!",
            "event": event
        }),
    }
