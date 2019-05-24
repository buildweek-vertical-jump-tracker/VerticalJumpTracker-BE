# VerticalJumpTracker-BE


For More detailed descriptions, visit https://awsafran-vertical.herokuapp.com/swagger-ui.html#/

###ENDPOINTS - GET:


/users/me

Retreive all data for currently logged in user

/workouts/all

Retreive all workout data

/workouts/today

Retreives user's currently assigned workout

/workouts/{id}

increments user's number of workouts completed - used get request as it is the most simple to implement


###ENDPOINTS: POST:

/goals/{userid}

Adds a new goal for the given user and returns a list of all goals for that user

/users

Adds a new user to the database

###ENDPOINTS: PUT:

/goals/update/{goalid}

updates the given goal by changing the goal height or changing its completion status

###ENPOINTS: DELETE:

/goals/delete/{goalid}

Deletes the given goal