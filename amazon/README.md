## This repository implements Amazon's UID generator service. 
1. Make sure Docker Desktop is installed along with docker-compose.
2. Run the following command in the root directory of the project.
```bash 
docker-compose -f docker-compose.yml up -d
```
3. Run the application
4. Use following curl to test it out.
```bash
curl --location 'localhost:8090/v1/uid/range?limit=800&client=PAYMENTS'
```