package 

import (
    ".Tags/lib/context"
)

type TagsRepo struct {
    TagsList []model.Tags
    datasetSize int
}

func NewTagsRepository() *TagsRepo {
    return &TagsRepo {
        TagsList: make([]model.Tags, 0),
        datasetSize: 30,
    }
}

    func (r *TagsRepo) TagsSearchGet() {

    }
    func (r *TagsRepo) TagsTagNameGet() {

    }
    func (r *TagsRepo) TagsTagNameMediaRecentGet() {

    }
