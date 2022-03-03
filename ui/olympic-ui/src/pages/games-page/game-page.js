import React, {Component} from 'react';

import TeamRatingTable from "../../components/team-rating-table/team-rating-table";
import GameMainInfo from "../../components/game-main-info/game-main-info";

import {Container, Grid} from "@mui/material";
import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";

import GameService from '../../services/game-service';
import ResultsService from "../../services/results-service";


class GamePage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            game: null,
            rating: [],
            athleteCount: 0,
            sportCount: 0
        }
    }

    componentDidMount() {
        this.getGame(this.props.match.params.id);
    }

    getGame = (id) => {
        GameService.getGameById(id)
            .then(response => {
                this.setState({game: response.data});
            })
            .catch(e => console.log(e))
        ResultsService.getCountryTeamRatingByGame(id)
            .then(response => {
                this.setState({rating: response.data})})
            .catch(e => console.log(e));
        GameService.getAthletesCountByGameId(id)
            .then(response => {
                this.setState({athleteCount: response.data.athleteCount});
            })
            .catch(e => console.log(e));
        GameService.getSportsCountByGameId(id)
            .then(response => {
                this.setState({sportCount: response.data.sportCount});
            })
            .catch(e => console.log(e));
    }

    render() {

        const {game, rating, athleteCount, sportCount} = this.state;

        return (
            <Container maxWidth="lg">
                <h1 style={{"text-transform":"uppercase", "text-align":"center", "margin-top": "80px"}}>Game Info</h1>
                <Grid container spacing={2}>
                    <Grid item xs={6}>
                        <div style={{"text-align":"center"}}>
                            <img
                                src={game && game.thumbnailUrl}
                                alt={game && game.city}
                            />
                        </div>
                    </Grid>
                    <Grid item xs={6}>
                         <GameMainInfo
                             game={game}
                             rating={rating}
                             athleteCount={athleteCount}
                             sportCount={sportCount}
                         />
                    </Grid>
                </Grid>
                <Grid container spacing={1}>
                    <Grid item xs={12}>
                        <TeamRatingTable rating={rating} />
                    </Grid>
                </Grid>
            </Container>
        );
    }
}

export default withStyles(styles)(GamePage);