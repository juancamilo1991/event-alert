import { Button, Card, CardActions, CardContent, ThemeProvider, createTheme } from '@mui/material';
import React, { useState } from 'react'
import { RequestError } from '../../types/types';

type RequestErrorProps = {
  error: RequestError | undefined;
  closeCard: (closed: undefined) => void;
}

const RequestErrorCard = (props: RequestErrorProps) => {

  const cardStyle = {
    backgroundColor: '#E41C39',
    cardContents: '#fff',
  }

  return (
    <div>
        <Card style={{ backgroundColor: cardStyle.backgroundColor }}>
          <CardContent>
            <span style={{ color: cardStyle.cardContents }}>Felhercode: {props.error?.myStatus}</span><br />
            <span style={{ color: cardStyle.cardContents }}>{props.error?.message}</span>
          </CardContent>
          <CardActions>
            <span style={{ color: cardStyle.cardContents, cursor: 'pointer' }} onClick={() => props.closeCard(undefined)}>&times;</span>
          </CardActions>
        </Card>
    </div>
  )
}

export default RequestErrorCard;