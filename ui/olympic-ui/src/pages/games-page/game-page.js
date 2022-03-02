import React, {Component} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableRow from '@mui/material/TableRow';
import {Container, Grid, Paper} from "@mui/material";
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
            athleteCount: 0
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
    }

    render() {

        const {game, rating, athleteCount} = this.state;

        const {classes} = this.state;

        return (
            <Container maxWidth="lg">
                <h1 style={{"text-transform":"uppercase", "text-align":"center", "margin-top": "80px"}}>Game Info</h1>
                <Grid container spacing={2}>
                    <Grid item xs={4}>
                        <div>
                            <img
                                src={game && game.thumbnailUrl}
                                alt={game && game.city}
                            />
                        </div>
                    </Grid>
                    <Grid item xs={8}>
                            <TableContainer component={Paper}>
                                <Table aria-label="simple table">
                                    <TableBody>
                                        <TableRow
                                            key={"city"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">City</TableCell>
                                            <TableCell align="center">{game && game.city}</TableCell>
                                        </TableRow>
                                        <TableRow
                                            key={"year"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Year</TableCell>
                                            <TableCell align="center">{game && game.year}</TableCell>
                                        </TableRow>
                                        <TableRow
                                            key={"type"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Season</TableCell>
                                            <TableCell align="center">{game && game.type}</TableCell>
                                        </TableRow>
                                        <TableRow
                                            key={"type"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Country's Teams</TableCell>
                                            <TableCell align="center">{rating && rating.length}</TableCell>
                                        </TableRow>
                                        <TableRow
                                            key={"type"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Athletes</TableCell>
                                            <TableCell align="center">{athleteCount}</TableCell>
                                        </TableRow>
                                    </TableBody>
                                </Table>
                            </TableContainer>
                    </Grid>
                </Grid>
            </Container>
        );
    }
}

export default withStyles(styles)(GamePage);