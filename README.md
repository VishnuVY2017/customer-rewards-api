# customer-rewards-api

## Add Customer
```
localhost:8181/customer

Body
----
{
    "name":"Deepak Sharma"
}
Response
--------
{
    "cid": 3,
    "name": "Deepak Sharma",
    "transactions": null,
    "rewardPoints": 0,
    "totalAmount": 0.0
}
```

## Add Transaction with based on customer id
```
localhost:8181/transaction/customer/1
Body
----
{
    "total":120,
    "description":"Spec bill 120 for internet"
}

Response
--------
{
    "points": 90,
    "tid": 4,
    "total": 120.0,
    "description": "Spec bill 120 for internet",
    "dateTime": "2025-06-17T17:55:04.235"
}
```

## Display all customer data with Transaction
```
localhost:8181/customer/

Response
--------
[
    {
        "cid": 1,
        "name": "Deepak Kangra",
        "transactions": [
            {
                "points": 850,
                "tid": 1,
                "total": 500.0,
                "description": "Spec bill 110 for internet",
                "dateTime": "2025-06-17T17:54:39.101"
            },
            {
                "points": 850,
                "tid": 2,
                "total": 500.0,
                "description": "Spec bill 500 for internet",
                "dateTime": "2025-06-17T17:54:44.516"
            }
        ],
        "rewardPoints": 1700,
        "totalAmount": 1000.0
    },
    {
        "cid": 2,
        "name": "Deepak Singh",
        "transactions": [
            {
                "points": 570,
                "tid": 3,
                "total": 360.0,
                "description": "Spec bill 360 for internet",
                "dateTime": "2025-06-17T17:54:55.984"
            }
        ],
        "rewardPoints": 570,
        "totalAmount": 360.0
    },
    {
        "cid": 3,
        "name": "Deepak Sharma",
        "transactions": [
            {
                "points": 90,
                "tid": 4,
                "total": 120.0,
                "description": "Spec bill 120 for internet",
                "dateTime": "2025-06-17T17:55:04.235"
            }
        ],
        "rewardPoints": 90,
        "totalAmount": 120.0
    }
]
```
## Filter Data based on last 3 months

```
localhost:8181/customer/2/month/3
Response
-------
{
    "cid": 2,
    "name": "Deepak Singh",
    "transactions": [
        {
            "points": 570,
            "tid": 3,
            "total": 360.0,
            "description": "Spec bill 360 for internet",
            "dateTime": "2025-06-17T17:54:55.984"
        }
    ],
    "rewardPoints": 570,
    "totalAmount": 360.0
}
```


