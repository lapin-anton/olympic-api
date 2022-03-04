import React, {Component} from 'react';

import TeamRatingTable from "../../components/team-rating-table/team-rating-table";
import GameMainInfo from "../../components/game-main-info/game-main-info";
import TopAthleteTable from "../../components/top-athletes-table/top-athletes-table";

import {
    Container,
    Grid,
    Accordion,
    AccordionSummary,
    AccordionDetails
} from "@mui/material";
import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";

import GameService from '../../services/game-service';
import ResultsService from "../../services/results-service";
import AthleteService from "../../services/athlete-service";

class GamePage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            game: null,
            teamRating: [],
            athleteRating: [],
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
                this.setState({teamRating: response.data})})
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
        AthleteService.getTop50AthletesByGame(id)
            .then(response => {
                this.setState({athleteRating: response.data})
            })
            .catch(e => console.log(e));
    }

    render() {

        const {game, teamRating, athleteRating, athleteCount, sportCount} = this.state;

        const {classes} = this.props;

        return (
            <Container maxWidth="lg">
                <h1 style={{"text-transform":"uppercase", "text-align":"center", "margin-top": "80px"}}>Game Info</h1>
                <Grid container spacing={2} style={{"margin-bottom":"10px"}}>
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
                             teamCount={teamRating && teamRating.length}
                             athleteCount={athleteCount}
                             sportCount={sportCount}
                         />
                    </Grid>
                </Grid>
                <Grid container spacing={1}>
                    <Grid item xs={12}>
                        <Accordion>
                            <AccordionSummary>
                                <h2 className={classes.listHead}>Unofficial Team Medal Count</h2>
                            </AccordionSummary>
                            <AccordionDetails>
                                <TeamRatingTable rating={teamRating} />
                            </AccordionDetails>
                        </Accordion>
                        <Accordion>
                            <AccordionSummary>
                                <h2 className={classes.listHead}>Top 50 Athletes</h2>
                            </AccordionSummary>
                            <AccordionDetails>
                                <TopAthleteTable rating={athleteRating}/>
                            </AccordionDetails>
                        </Accordion>
                    </Grid>
                </Grid>
            </Container>
        );
    }
}

export default withStyles(styles)(GamePage);