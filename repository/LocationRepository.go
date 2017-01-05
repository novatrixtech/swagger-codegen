package 

import (
    ".Location/lib/context"
)

type LocationRepo struct {
    LocationList []model.Location
    datasetSize int
}

func NewLocationRepository() *LocationRepo {
    return &LocationRepo {
        LocationList: make([]model.Location, 0),
        datasetSize: 30,
    }
}

    func (r *LocationRepo) LocationsLocationIdGet() {

    }
    func (r *LocationRepo) LocationsLocationIdMediaRecentGet() {

    }
    func (r *LocationRepo) LocationsSearchGet() {

    }
