package 

import (
    ".Default/lib/context"
)

type DefaultRepo struct {
    DefaultList []model.Default
    datasetSize int
}

func NewDefaultRepository() *DefaultRepo {
    return &DefaultRepo {
        DefaultList: make([]model.Default, 0),
        datasetSize: 30,
    }
}

    func (r *DefaultRepo) GeographiesGeoIdMediaRecentGet() {

    }
