import React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';

import { withStyles } from '@mui/styles';

import {styles} from "../../css-common";

function GameCard(props) {

    const {img, city, year, season, classes} = props;

    return (
        <Card>
            <CardActionArea>
                <CardMedia
                    component="img"
                    alt={city}
                    sx={{width:'inherit'}}
                    image={img}
                />
                <CardContent className={classes.cardContent}>
                    <Typography gutterBottom variant="h5" component="div">
                        {city}
                    </Typography>
                    <Typography variant="h5" color="div">
                        {year}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        {season}
                    </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    );
}

export default withStyles(styles) (GameCard);