import React, {Component} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableRow from '@mui/material/TableRow';
import {Container, Grid, Paper, TableFooter, TableHead, TablePagination} from "@mui/material";
import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";

import GameService from '../../services/game-service';
import ResultsService from "../../services/results-service";
import TablePaginationActions from "@mui/material/TablePagination/TablePaginationActions";


class GamePage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            game: null,
            rating: [],
            athleteCount: 0,
            rowsPerPage: 5,
            page: 0
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

    handleChangePage = (event, newPage) => {
        this.setState({
            page: newPage
        });
    };

    handleChangeRowsPerPage = (event) => {
        this.setState({
            rowsPerPage: parseInt(event.target.value, 10),
            page: 0
        });
    };

    render() {

        const {game, rating, athleteCount, rowsPerPage, page} = this.state;

        const {classes} = this.props;

        const emptyRows = page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rating.length) : 0;

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
                                        <TableRow
                                            key={"type"}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">Sports</TableCell>
                                            <TableCell align="center">111</TableCell>
                                        </TableRow>
                                    </TableBody>
                                </Table>
                            </TableContainer>
                    </Grid>
                </Grid>
                <Grid container spacing={1}>
                    <Grid item xs={12}>
                        <h2 className={classes.listHead}>Unofficial Team Medal Count</h2>
                        <TableContainer component={Paper}>
                            <Table aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell align="center">Place</TableCell>
                                        <TableCell align="center">Country's Team</TableCell>
                                        <TableCell align="center">Gold</TableCell>
                                        <TableCell align="center">Silver</TableCell>
                                        <TableCell align="center">Bronze</TableCell>
                                        <TableCell align="center">Total</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {(rowsPerPage > 0
                                            ? rating.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                            : rating
                                    ).map((r, i) => {

                                        const place = page * rowsPerPage + i + 1;

                                        let rowStyle = classes.commonRowColor;

                                        if (place === 1) {
                                            rowStyle = classes.goldColor;
                                        }

                                        if (place === 2) {
                                            rowStyle = classes.silverColor;
                                        }

                                        if (place === 3) {
                                            rowStyle = classes.bronzeColor;
                                        }

                                        return (<TableRow className={rowStyle}>
                                            <TableCell align="center">{place}</TableCell>
                                            <TableCell align="center">{r.country}</TableCell>
                                            <TableCell align="center">{r.gold}</TableCell>
                                            <TableCell align="center">{r.silver}</TableCell>
                                            <TableCell align="center">{r.bronze}</TableCell>
                                            <TableCell align="center">{r.total}</TableCell>
                                        </TableRow>)
                                    })}

                                    {emptyRows > 0 && (
                                        <TableRow style={{ height: 53 * emptyRows }}>
                                            <TableCell colSpan={6} />
                                        </TableRow>
                                    )}
                                </TableBody>
                                <TableFooter>
                                    <TableRow>
                                        <TablePagination
                                            rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
                                            colSpan={3}
                                            count={rating.length}
                                            rowsPerPage={rowsPerPage}
                                            page={page}
                                            SelectProps={{
                                                inputProps: {
                                                    'aria-label': 'rows per page',
                                                },
                                                native: true,
                                            }}
                                            onPageChange={this.handleChangePage}
                                            onRowsPerPageChange={this.handleChangeRowsPerPage}
                                            ActionsComponent={TablePaginationActions}
                                        />
                                    </TableRow>
                                </TableFooter>
                            </Table>
                        </TableContainer>
                    </Grid>
                </Grid>
            </Container>
        );
    }
}

export default withStyles(styles)(GamePage);