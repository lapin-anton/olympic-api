import React, {Component} from 'react';

import TablePaginationActions from "@mui/material/TablePagination/TablePaginationActions";

import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";
import {
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableFooter,
    TableHead,
    TablePagination,
    TableRow
} from "@mui/material";

class TopAthleteTable extends Component {

    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            rowsPerPage: 5
        }
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

        const {classes, rating} = this.props;

        const {page, rowsPerPage} = this.state;

        const emptyRows = page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rating.length) : 0;

        return (
            <>
                <h2 className={classes.listHead}>Top 50 Athletes</h2>
                <TableContainer component={Paper}>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow className={classes.headRow}>
                                <TableCell align="center">Place</TableCell>
                                <TableCell align="center">Name</TableCell>
                                <TableCell align="center">Surname</TableCell>
                                <TableCell align="center">Age</TableCell>
                                <TableCell align="center">Country</TableCell>
                                <TableCell align="center">Team Rank</TableCell>
                                <TableCell align="center">Sport</TableCell>
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
                                    <TableCell align="center">{r.name}</TableCell>
                                    <TableCell align="center">{r.surname}</TableCell>
                                    <TableCell align="center">{r.age}</TableCell>
                                    <TableCell align="center">{r.country}</TableCell>
                                    <TableCell align="center">{r.teamRank}</TableCell>
                                    <TableCell align="center">{r.sport}</TableCell>
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
            </>
        );
    }

}

export default withStyles(styles) (TopAthleteTable);