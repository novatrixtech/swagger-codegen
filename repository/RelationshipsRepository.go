package 

import (
    ".Relationships/lib/context"
)

type RelationshipsRepo struct {
    RelationshipsList []model.Relationships
    datasetSize int
}

func NewRelationshipsRepository() *RelationshipsRepo {
    return &RelationshipsRepo {
        RelationshipsList: make([]model.Relationships, 0),
        datasetSize: 30,
    }
}

    func (r *RelationshipsRepo) UsersSelfRequestedByGet() {

    }
    func (r *RelationshipsRepo) UsersUserIdFollowedByGet() {

    }
    func (r *RelationshipsRepo) UsersUserIdFollowsGet() {

    }
    func (r *RelationshipsRepo) UsersUserIdRelationshipPost() {

    }
